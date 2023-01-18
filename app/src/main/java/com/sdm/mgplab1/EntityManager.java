package com.sdm.mgplab1;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

// Created by TanSiewLan2021

public class EntityManager {

    public final static EntityManager Instance = new EntityManager();
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();
    private SurfaceView view = null;
    private SensorManager sensor;

    private EntityManager()
    {
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
       // sensor = (SensorManager)_view.getContext().getSystemService(Context.SENSOR_SERVICE);
       // sensor.registerListener((SensorEventListener) this, sensor.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
       //         SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void Update(float _dt)
    {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        // Update all
        for(int i = 0; i < entityList.size(); ++i)
        {
            // Lets check if is init, initialize if not
            if (!entityList.get(i).IsInit())
            {
                entityList.get(i).Init(view);
            }

            entityList.get(i).Update(_dt);

            // Check if need to clean up
            if (entityList.get(i).IsDone()) {
                // Done! Time to add to the removal list
                removalList.add(entityList.get(i));
            }
        }

        // Remove all entities that are done
        for (EntityBase currEntity : removalList) {
            entityList.remove(currEntity);
        }
        removalList.clear(); // Clean up of removal list

        // Collision Check
        for (int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if (currEntity instanceof Collidable)
            {
                Collidable first = (Collidable) currEntity;

                for (int j = i+1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if (otherEntity instanceof Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;

                        PlayerEntity.Falling = true;
                        if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(), first.GetRadius(), second.GetPosX(), second.GetPosY(), second.GetRadius()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                        PlayerEntity.Falling = true;

                        if(Collision.AABB(first.GetPosX(), first.GetPosY(),first.GetWidth(), first.GetHeight(), second.GetPosX(), second.GetPosY(), second.GetWidth(), second.GetHeight())
                                && Collision.CollideWithSide(first.GetPosX(), first.GetPosY(),first.GetWidth(), first.GetHeight(), second.GetPosX(), second.GetPosY(), second.GetWidth(), second.GetHeight()))
                        {
                            //System.out.println(first.GetHeight());
                            first.OnBoxHit(second);
                            second.OnBoxHit(first);
                        }
                    }
                }
            }

            // Check if need to clean up
            if (currEntity.IsDone()) {
                removalList.add(currEntity);
            }
        }

        // Remove all entities that are done
        for (EntityBase currEntity : removalList) {
            entityList.remove(currEntity);
        }
        removalList.clear();
    }

    public void Render(Canvas _canvas)
    {
      
        // Use the new "rendering layer" to sort the render order
        Collections.sort(entityList, new Comparator<EntityBase>() {
            @Override
            public int compare(EntityBase o1, EntityBase o2) {
                return o1.GetRenderLayer() - o2.GetRenderLayer();
            }
        });

        for(int i = 0; i <entityList.size(); ++i)
        {
            entityList.get(i).Render(_canvas);
        }
    }

    public void AddEntity(EntityBase _newEntity, EntityBase.ENTITY_TYPE entity_type)
    {
        entityList.add(_newEntity);
    }

    public void Clean()
    {
        entityList.clear();
    }
}


